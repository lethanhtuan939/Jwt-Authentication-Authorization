package vn.LeThanhTuan.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    public String get() {
        return "GET admin controller";
    }
    @PostMapping
    public String post() {
        return "GET admin controller";
    }
    @PutMapping
    public String put() {
        return "GET admin controller";
    }
    @DeleteMapping
    public String delete() {
        return "GET admin controller";
    }
}
