package vn.LeThanhTuan.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController {

    @GetMapping
    public String get() {
        return "GET management controller";
    }
    @PostMapping
    public String post() {
        return "GET management controller";
    }
    @PutMapping
    public String put() {
        return "GET management controller";
    }
    @DeleteMapping
    public String delete() {
        return "GET management controller";
    }
}
