package jsingle;

import java.util.List;

public class Main {
    public static void main(String... args) {
        Rectangle r = new Rectangle(1, 2, 3, 4);
        Circle c = new Circle(5, 6, 7);

        CompoundShape cShape = new CompoundShape();
        cShape.shapeList = List.of(r, c);

        Visitor visitor = new TranslationVisitor(8, 9);
        visitor.visit(cShape);
    }
}

abstract class Shape {
    abstract void accept(Visitor visitor);
}

class Circle extends Shape {
    int x;
    int y;
    int radius;

    Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle extends Shape {
    int x;
    int y;
    int width;
    int height;

    Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class CompoundShape extends Shape {
    List<Shape> shapeList = List.of();

    @Override
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

abstract class Visitor {
    abstract void visit(Circle c);

    abstract void visit(Rectangle r);

    void visit(CompoundShape c) {
        for (Shape s : c.shapeList) {
            s.accept(this);
        }
    }
}

class TranslationVisitor extends Visitor {
    int dx;
    int dy;

    TranslationVisitor(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    void visit(Circle c) {
        c.x += dx;
        c.y += dy;
    }

    @Override
    void visit(Rectangle r) {
        r.x += dx;
        r.y += dy;
    }
}

class PrintVisitor extends Visitor {
    @Override
    void visit(Circle c) {
        System.out.println("visiting circle: " + c);
    }

    @Override
    void visit(Rectangle r) {
        System.out.println("visiting rectangle: " + r);
    }

    @Override
    void visit(CompoundShape c) {
        System.out.println("visiting compound shape: " + c);
        super.visit(c);
    }
}
