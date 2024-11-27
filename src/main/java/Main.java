import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String name;
    private double speed;

    public Car(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double distance() {
        return speed * 24;
    }
}

class Race {
    private List<Car> cars;

    public Race() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Car Leader() {
        if (cars.isEmpty()) {
            return null;
        }
        Car leader = cars.get(0);
        for (Car car : cars) {
            if (car.distance() > leader.distance()) {
                leader = car;
            }
        }
        return leader;
    }
}

public class Main {
    private static Car getCarData() {
        Scanner scanner = new Scanner(System.in);
        String name;
        double speed;

        while (true) {
            System.out.print("Введите название автомобиля: ");
            name = scanner.nextLine();

            System.out.print("Введите скорость автомобиля: ");
            String speedInput = scanner.nextLine();

            try {
                if (speedInput.contains(".")) {
                    throw new NumberFormatException("Число с плавающей точкой не допустимо.");
                }
                speed = Double.parseDouble(speedInput);
                if (speed > 0 && speed <= 250) {
                    return new Car(name, speed);
                } else {
                    System.out.println("Неправильная скорость");
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число для скорости.");
            }
        }
    }

    public static void main(String[] args) {
        Race race = new Race();

        for (int i = 0; i < 3; i++) {
            Car car = getCarData();
            race.addCar(car);
        }

        Car leader = race.Leader();
        if (leader != null) {
            System.out.println("Самая быстрая машина: " + leader.getName());
        }
    }
}