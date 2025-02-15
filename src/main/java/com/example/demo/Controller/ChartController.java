package com.example.demo.Controller;

import com.example.demo.Service.ChartService;
import com.example.demo.model.HiraganaChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chart")
public class ChartController {

    @Autowired
    ChartService chartService;

    @GetMapping("/hiragana")
    public HiraganaChart getHiraganaChart()
    {
        HiraganaChart chart = chartService.generateHiraganaChart();
        return chart;
    }
}
