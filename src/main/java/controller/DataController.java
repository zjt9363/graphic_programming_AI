package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Zarrow
 */
/*
@RestController
@RequestMapping("/api")
public class DataController {
    @RequestMapping("/save")
    public String save(@RequestBody ArrayList<Object> arrayList){
        System.out.println(arrayList);
        System.out.println("Hello");
        return "{'user':'name'}";
    }
}
*/

@Controller
@ResponseBody
@RequestMapping("/api")
public class DataController {

    @PostMapping("/save")
    public String test(@RequestBody ArrayList<Object> arrayList) {
        System.out.println(arrayList);
        System.out.println(arrayList.get(1));
        return "123";
    }

    @GetMapping("/get")
    public String test() {

        System.out.println("hello");
        return "123";
    }

    @RequestMapping("/paramForJson")
    @ResponseBody
    public String param(@RequestBody List<String> list){
        System.out.println(list);
        return "accept";
    }
}
