package com.example.android.controller;

import com.example.android.model.Order;
import com.example.android.model.ShippingInformation;
import com.example.android.model.Statistic;
import com.example.android.model.StatisticOrder;
import com.example.android.repository.OrderRepository;
import com.example.android.repository.ShippingInformationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/getRevenueOfSeller/{id}")
    public StatisticOrder getDefault(@PathVariable("id") String id) {
        StatisticOrder statisticOrder = new StatisticOrder();
        List<Order> orderList = orderRepository.getOrderSuccessByIdSeller(id);
        long totalAmount = 0;
        int totalQuantity = 0;
        for (Order item : orderList) {
            totalAmount += item.getTotalPrice();
            totalQuantity += item.getTotalQuantity();
        }
        statisticOrder.setStatisticAmount(totalAmount);
        statisticOrder.setStatisticQuantity(totalQuantity);


        return statisticOrder;
    }

    @GetMapping("/getStatistic/{id}")
    public Statistic statistic(@PathVariable("id") String id) throws ParseException {
        List<Order> listOrder = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        LocalDate today = LocalDate.now();
        LocalDate monday = today;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }

        // Go forward to get Sunday
        LocalDate sunday = today;
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.plusDays(1);
        }
        sunday = sunday.plusDays(1);

        String month = today.toString().substring(0, today.toString().length() - 3);

        Date date1 = format.parse(monday + " 00:00:00");
        Date date2 = format.parse(sunday + " 00:00:00");
        List<Order> listBillMonth = orderRepository.getDateLike(month, id);
        List<Order> listBillWeek = orderRepository.getDateBetween1(date1, date2, id);
        List<Order> listBillToday = orderRepository.getDateLike(today.toString(), id);
        int sumMon = 0, sumToday = 0, sumWeek = 0;
        for (Order item : listBillMonth) {
            sumMon += item.getTotalPrice();
        }
//
        for (Order item : listBillToday) {
            sumToday += item.getTotalPrice();
        }
//
        for (Order item : listBillWeek) {
            sumWeek += item.getTotalPrice();
        }
        Statistic statisticResult = new Statistic();
        statisticResult.setTotalSumToday(sumToday);
        statisticResult.setTotalSumWeek(sumWeek);
        statisticResult.setTotalSumMonth(sumMon);


        return statisticResult;
    }

    @GetMapping("/getStatisticByTime")
    public List<Order> getStatisticByTime(
            @RequestParam("start") String start,
            @RequestParam("end") String end,
            @RequestParam("id_seller") String idSeller
    ) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1 = format.parse(start + " 00:00:00");
        Date date2 = format.parse(end + " 00:00:00");
        List<Order> listOrder = orderRepository.getDateBetween1(date1, date2, idSeller);

        return listOrder;
    }


}
