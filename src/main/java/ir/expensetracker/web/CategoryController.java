package ir.expensetracker.web;

import ir.expensetracker.api.CategoryCreateParam;
import ir.expensetracker.api.ErrorResult;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.service.facade.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryCreateParam category, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(category, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCategories(@RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(categoryService.findAllCategories(jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

}
