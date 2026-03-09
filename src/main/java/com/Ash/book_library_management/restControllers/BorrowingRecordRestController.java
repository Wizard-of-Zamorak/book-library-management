package com.Ash.book_library_management.restControllers;


import com.Ash.book_library_management.entity_DTO.BorrowingRecordDTO;
import com.Ash.book_library_management.service.BorrowingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingRecordRestController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordRestController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @GetMapping
    public ResponseEntity<List<BorrowingRecordDTO>> getAllBorrowingRecords() {
        List<BorrowingRecordDTO> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
        return ResponseEntity.ok(borrowingRecords);
    }

    @PostMapping
    public ResponseEntity<BorrowingRecordDTO> borrowBook(@RequestBody BorrowingRecordDTO borrowingRecordDTO){
        BorrowingRecordDTO borrowedRecordDTO = borrowingRecordService.borrowBook(borrowingRecordDTO);
        return new ResponseEntity<>(borrowedRecordDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingRecordDTO> returnBook(@PathVariable Long id) {
        BorrowingRecordDTO returnedRecordDTO = borrowingRecordService.returnBook(id);
        return ResponseEntity.ok(returnedRecordDTO);
    }
}
