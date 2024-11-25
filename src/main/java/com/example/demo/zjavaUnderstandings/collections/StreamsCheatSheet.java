package com.example.demo.zjavaUnderstandings.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamsCheatSheet {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDataBase.getAllEmployees();
        log.info(employees.toString());


// ----------------> for each implementation of streams
        employees.forEach(e -> log.info("{}:{}",e.getName(), e.getSalary()));

// ----------------> or for each as below can be performed
        employees.stream()./*perform operations as required and then for each*/
        forEach(e -> System.out.println(e.getName() +" : "+ e.getSalary()));

//-----------------> filter
        employees.stream()
                .filter(e-> e.getDept().equals("Development") && e.getSalary()>80000.00)
                .toList()
                .forEach(e -> System.out.println(e.getName()));

//----------------> map
        /*use set for distinct elements storing*/
        /*map is used to store elements into a collection*/

        Set<String> departmentList = employees.stream()
                .map(Employee::getDept)
                .collect(Collectors.toSet());
        System.out.println(departmentList);

//----------------> flat map
        /*flat map is used to get attributes of a nested class values*/

        List<String> distinctProjectsList = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .map(Project::getName)
                .collect(Collectors.toList());

        System.out.println(distinctProjectsList);

//---------------> sorting - ascending

        List<Employee> sortedEmployeeList = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
        System.out.println(sortedEmployeeList);

//---------------> sorting - descending

        List<Double> salaryList = employees.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Employee::getSalary)))
                .map(Employee::getSalary)
                .toList();
        System.out.println(salaryList);

//---------------> min & max

        List<Double> minSalaryString = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .stream()
                .map(Employee::getSalary)
                .toList();
        System.out.println(minSalaryString);

//---------------> max

        List<Double> maxSalaryString = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .stream()
                .map(Employee::getSalary)
                .toList();
        System.out.println(maxSalaryString);

//-------------> grouping by

        Map<String, List<Employee>> employeeGenderMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender));
        System.out.println(employeeGenderMap);

//-------------->
//      [Gender] -> Names
        Map<String, List<String>> genderNameMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(genderNameMap);

//-----------------> grouping by --> count

        Map<String, Long> genderCountMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(genderCountMap);

//-----------------> find first
        Employee firstDevelopmentEmployee = employees.stream()
                .filter(e -> e.getDept().equals("Development"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        System.out.println(firstDevelopmentEmployee);
//------------------> find Any
        Employee developementEmployee = employees.stream()
                .filter(e -> e.getDept().equals("Development"))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        System.out.println(developementEmployee);
//--------------------> Any Match
        boolean developmentAnyMatch = employees.stream()
                .anyMatch(e -> e.getDept().equals("Development"));
        System.out.println("is there a development employee: " + developmentAnyMatch);
//--------------------> All Match
        boolean developmentAllMatch = employees.stream()
                .allMatch(e -> e.getDept().equals("Development"));
        System.out.println("are the all the employees are developers: "+ developmentAllMatch);
//--------------------> salary all match check
        boolean salaryGreaterThan55k = employees.stream()
                .allMatch(e -> e.getSalary()>50000);
        System.out.println("all the employees are having salary more than 50k: "+ salaryGreaterThan55k);
//---------------------> None Match
        boolean noneMatchCase = employees.stream()
                .noneMatch(e -> e.getDept().equals("abc"));
        System.out.println("is not match:" +noneMatchCase);
//----------------------> limit case
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(4)
                .forEach(e -> System.out.println(e.getName() +" : "+e.getSalary()));
    }
}