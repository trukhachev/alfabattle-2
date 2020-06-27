package com.trukhachev.branch.controller;

import com.trukhachev.branch.dto.NotFoundDTO;
import com.trukhachev.branch.handler.BranchHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchHandler branchHandler;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getBranch(@PathVariable("id") String id) {
        var result = branchHandler.getBranch(id);

        if (result == null) {
            return new ResponseEntity<>(new NotFoundDTO().setStatus("branch not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getNearest(@RequestParam(value = "lat") String latitude,
                                        @RequestParam(value = "lon") String longitude) {
        var result = branchHandler.getNearest(latitude, longitude);

        if (result == null) {
            return new ResponseEntity<>(new NotFoundDTO().setStatus("branch not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
