package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {

    @RequestMapping(value = "/add")
    public Response add() {
        System.out.println("Success");
        Response response = new Response();
        response.setValue("2");
        return response;
    }

    @RequestMapping(value = "/getDA")
    public Response getDA(@RequestParam(value = "order[]") ArrayList<String> order,
                          @RequestParam(value = "card[]") ArrayList<String> card,
                          @RequestParam(value = "bio[]") ArrayList<String> bio,
                          @RequestParam(value = "pin[]") ArrayList<String> pin,
                          @RequestParam(value = "rotP[]") ArrayList<String> rotP,
                          @RequestParam(value = "ultra[]") ArrayList<String> ultra,
                          @RequestParam(value = "rotN[]") ArrayList<String> rotN) {
        System.out.println("Request for DoA received");
        double doA = 0;
        if (!card.get(0).equals("true")) {
            doA += 3;
        }
        if (!bio.get(0).equals("true")) {
            doA += 3;
        }
        if (!pin.get(0).equals("true")) {
            doA += 3;
        }
        if (!rotP.get(0).equals("true")) {
            doA += 3;
        } else {
            double angDisMean = 80;
            double angDisSD = 20;
            double zAngDis = (Double.parseDouble(rotP.get(1)) - angDisMean) / angDisSD;
            double angDisWeight = 1;

            double durationMean = 3;
            double durationSD = 2;
            double zDuration = (Double.parseDouble(rotP.get(2)) - durationMean) / durationSD;
            double durationWeight = 0.25;

            doA += Math.abs(zAngDis) * angDisWeight + Math.abs(zDuration) * durationWeight;
        }
        if (!ultra.get(0).equals("true")) {
            doA += 3;
        } else {
            double avgDisMean = 40;
            double avgDisSD = 10;
            double zAngDis = (Double.parseDouble(ultra.get(1)) - avgDisMean) / avgDisSD;
            double angDisWeight = 0.5;

            double durationMean = 3;
            double durationSD = 1;
            double zDuration = (Double.parseDouble(ultra.get(2)) - durationMean) / durationSD;
            double durationWeight = 0.5;

            doA += Math.abs(zAngDis) * angDisWeight + Math.abs(zDuration) * durationWeight;
        }
        if (!rotN.get(0).equals("true")) {
            doA += 3;
        } else {
            double angDisMean = -80;
            double angDisSD = 20;
            double zAngDis = (Double.parseDouble(rotN.get(1)) - angDisMean) / angDisSD;
            double angDisWeight = 1;

            double durationMean = 3;
            double durationSD = 2;
            double zDuration = (Double.parseDouble(rotN.get(2)) - durationMean) / durationSD;
            double durationWeight = 0.25;

            doA += Math.abs(zAngDis) * angDisWeight + Math.abs(zDuration) * durationWeight;
        }

        if (rotP.get(0).equals("true") && rotN.get(0).equals("true")) {
            if (Math.abs(Double.parseDouble(rotP.get(1)) + Double.parseDouble(rotN.get(1))) > 1) {
                doA += 2;
            }
        }

        int misplacedCount = 0;
        for (int i = 0; i < order.size(); i++) {
            if (!order.get(i).equals("i" + (i + 1))) {
                misplacedCount++;
            }
        }
        doA += misplacedCount;

        doA = (double) Math.round(doA * 100) / 100;
        Response response = new Response();
        response.setValue("Degree of Abnormality = " + doA);

        if (doA < 3) {
            response.setSeverity("System working within tolerance");
        } else if (doA < 5) {
            response.setSeverity("Alert: Vulnerability detected");
        } else {
            response.setSeverity("Alert: Threat detected");
        }

        System.out.println("Setting response: Degree of Abnormality = " + doA);
        return response;
    }
}
