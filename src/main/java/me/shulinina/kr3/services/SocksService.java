package me.shulinina.kr3.services;
import me.shulinina.kr3.exception.InSufficientSockQuantityException;
import me.shulinina.kr3.exception.InvalidSockRequestException;
import me.shulinina.kr3.model.Color;
import me.shulinina.kr3.model.Size;
import me.shulinina.kr3.model.Socks;
import me.shulinina.kr3.model.Transaction;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
@Service
public class SocksService {
    private final Map<Socks, Integer> socksMap = new HashMap<>();
    public void addSock(Transaction transaction) {
        validateRequest(transaction);
        Socks socks = mapToSock(transaction);
        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + transaction.getQuantity());
        } else {
            socksMap.put(socks, transaction.getQuantity());
        }
    }
    public void issueSock(Transaction transaction) {
        decreaseSockQuantity(transaction);
    }
    public void removeDefectiveSocks(Transaction transaction) {
        decreaseSockQuantity(transaction);
    }
    private  void decreaseSockQuantity(Transaction transaction){
        validateRequest(transaction);
        Socks socks = mapToSock(transaction);
        int sockQuantity = socksMap.getOrDefault(socks, 0);
        if (sockQuantity >= transaction.getQuantity()) {
            socksMap.put(socks, sockQuantity - transaction.getQuantity());
        } else {
            throw new InSufficientSockQuantityException("Носков нет");
        }
    }
    public int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPart() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPart() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }
    private void validateRequest(Transaction transaction) {
        if (transaction.getColor() == null || transaction.getSize() == null) {
            throw new InvalidSockRequestException("Отметьте все поля");
        }
        if (transaction.getCottonPart() < 0 || transaction.getCottonPart() > 100) {
            throw new InvalidSockRequestException("укажите % хлопка от 0 до 100");
        }
        if (transaction.getQuantity() <= 0) {
            throw new InvalidSockRequestException("укажите верное количество");
        }
    }
    private Socks mapToSock(Transaction transaction) {
        return new Socks(
                transaction.getColor(),
                transaction.getSize(),
                transaction.getCottonPart());
    }
}