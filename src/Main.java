import entities.Employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        List<Employees> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] dados = line.split(",");
                String name = dados[0];
                String email = dados[1];
                Double salary = Double.valueOf(dados[2]);
                list.add(new Employees(name, email, salary));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.print("Enter salary: ");
        double salarySup = sc.nextInt();
        System.out.println("Email of people whose salary is more than: " + salarySup);

        List<String> salaryFilter = list.stream().filter(x -> x.getSalary() > salarySup)
                .map(x -> x.getEmail()).sorted().collect(Collectors.toList());

        salaryFilter.forEach(System.out::println);

        System.out.println("Sum of salary of people whose name starts with 'M': ");
        Double firstM = list.stream()
                .filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0, (x, y) -> x + y);

        System.out.println("Total dos salários dos nomes com M: " + firstM);

    }
}