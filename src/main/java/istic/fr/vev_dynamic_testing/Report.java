package istic.fr.vev_dynamic_testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Report {

    public static void print(Map<String, Integer> map) {
        map.forEach((b, n) -> System.out.println(b + "\n\t" + n));
    }

    public static void print(List<String> list) {
        list.forEach(System.out::println);
    }

    private List<Log> logs;

    public Report(List<Log> logs) {
        this.logs = logs;
    }

    public Map<String, Integer> nbBLockExectution() {
        Map<String, Integer> map = new HashMap<>();
        logs.stream().filter(Log::isDeclaringBlock).forEach(log -> {
            map.put(log.getMessage(), 0);
        });
        logs.stream().filter(log -> log.isBeginBlock()).forEach(log -> {
            map.put(log.getMessage(), map.get(log.getMessage()) + 1);
        });
        return map;
    }

    public List<String> methodCallSequence() {
        return logs.stream()
                .filter(log -> log.isBeginMethod())
                .map(Log::toString)
                .collect(Collectors.toList());
    }


}
