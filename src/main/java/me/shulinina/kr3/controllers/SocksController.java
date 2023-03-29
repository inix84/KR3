package me.shulinina.kr3.controllers;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.shulinina.kr3.model.Transaction;
import me.shulinina.kr3.exception.InSufficientSockQuantityException;
import me.shulinina.kr3.exception.InvalidSockRequestException;
import me.shulinina.kr3.model.Color;
import me.shulinina.kr3.model.Size;
import me.shulinina.kr3.services.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/sock")
@Tag(name = "Носки", description = "CRUD-операции для работы с товаром на складе")
public class SocksController {
    private final SocksService socksService;
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }
    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockRequestException invalidSockRequestException) {
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }
    @ExceptionHandler(InSufficientSockQuantityException.class)
    public ResponseEntity<String> handleInvalidException(InSufficientSockQuantityException invalidSockRequestException) {
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }
    @PostMapping("/add")
    public void addSock(@RequestBody Transaction transaction) {
        socksService.addSock(transaction);
    }
    @PutMapping("/issue")
    public void issueSocks(@RequestBody Transaction transaction) {
        socksService.issueSock(transaction);
    }
    @GetMapping("/get")
    public int getSocksCount(@RequestParam(required = false, name = "color") Color color,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return socksService.getSockQuantity(color, size, cottonMin, cottonMax);
    }
    @DeleteMapping("/delete")
    public void removeDefectiveSock(@RequestBody Transaction transaction){
        socksService.removeDefectiveSocks(transaction);
    }
}