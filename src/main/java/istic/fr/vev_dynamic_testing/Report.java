package istic.fr.vev_dynamic_testing;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Report {

    public static void print(Map<String, Long> map) {
        map.forEach((b, n) -> System.out.println(b + "\n\t" + n));
    }

    public static void print(List<String> list) {
        list.forEach(System.out::println);
    }

    private List<Log> logs;

    public Report(List<Log> logs) {
        this.logs = logs;
    }

    public Map<String, Long> nbBLockExectution() {
        return logs.stream()
                .filter(log -> log.isBeginBlock())
                .distinct()
                .collect(Collectors.groupingBy(Log::toString, Collectors.counting()));
    }

    public List<String> methodCallSequence() {
        return logs.stream()
                .filter(log -> log.isBeginMethod())
                .map(Log::toString)
                .collect(Collectors.toList());
    }


}
