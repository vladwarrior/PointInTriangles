import point.Point;
import triangle.Triangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Scanner scanner;
    private static ArrayList<Point> points;
    private static ArrayList<Triangle> triangles;
    private static String path = "tria-pt.txt";
    private static String line = "";
    private static File fileTest;


    public static void main(String[] args) throws FileNotFoundException {
        fileTest = new File(path);
        scanner = new Scanner(fileTest);
        readPoints(scanner);
        createTriangles();
        checkingTheAccessories();
    }

    private static void readPoints(Scanner scanner) {
        points = new ArrayList<Point>();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] pointCords = line.split("\\,");
            float[] floatsCords = new float[2];
            int counter = 0;

            for (String cords : pointCords) {
                floatsCords[counter++] = Float.parseFloat(cords);
            }
            points.add(new Point(floatsCords[0], floatsCords[1]));
        }
        scanner.close();
    }

    private static void createTriangles() {
        triangles = new ArrayList<Triangle>();

        for (int i = 0; i < points.size() / 4; i++) {
            triangles.add(new Triangle(
                    points.get(i * 4),
                    points.get(i * 4 + 1),
                    points.get(i * 4 + 2)));
        }

    }

    private static void checkingTheAccessories() {
        double a = 0, b = 0, c = 0;

        for (int i = 0; i < triangles.size(); i++) {
            a = 0; b = 0; c = 0;

            a = (triangles.get(i).getFirstPoint().getX() - points.get(4*i+3).getX()) *
                    (triangles.get(i).getSecondPoint().getY() - triangles.get(i).getFirstPoint().getY()) -
                    (triangles.get(i).getSecondPoint().getX() - triangles.get(i).getFirstPoint().getX()) *
                            (triangles.get(i).getFirstPoint().getY() - points.get(4*i+3).getY());

            b = (triangles.get(i).getSecondPoint().getX() - points.get(4*i+3).getX()) *
                    (triangles.get(i).getThirdPoint().getY() - triangles.get(i).getSecondPoint().getY()) -
                    (triangles.get(i).getThirdPoint().getX() - triangles.get(i).getSecondPoint().getX()) *
                            (triangles.get(i).getSecondPoint().getY() - points.get(4*i+3).getY());

            c = (triangles.get(i).getThirdPoint().getX() - points.get(4*i+3).getX()) *
                    (triangles.get(i).getFirstPoint().getY() - triangles.get(i).getThirdPoint().getY()) -
                    (triangles.get(i).getFirstPoint().getX() - triangles.get(i).getThirdPoint().getX()) *
                            (triangles.get(i).getThirdPoint().getY() - points.get(4*i+3).getY());

            if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
                System.out.println( i+1 + ": да");
            }
            else System.out.println(i+1 + ": нет");
        }
    }
}