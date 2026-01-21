package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Benchmarks {
    @Param({
            "java (single dispatch)",
            "groovy (single dispatch)",
            "groovy (double dispatch)",
    })
    String language;

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void visitor() {
        switch (language) {
            case "java (single dispatch)":
                jsingle.Main.main();
                break;
            case "groovy (single dispatch)":
                gsingle.Main.main();
                break;
            case "groovy (double dispatch)":
                gdouble.Main.main();
                break;
        }
    }
}
