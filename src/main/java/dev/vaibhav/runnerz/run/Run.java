package dev.vaibhav.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.Duration;
import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer kilometers,
        Location location
)  //gets all info required
{
    public Run {
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("completed on must be after started on");
        }
    }

    public Duration getDuration() {
        return Duration.between(startedOn,completedOn);
    }

    public Integer getAvgPace() {
        return Math.toIntExact(getDuration().toMinutes() / kilometers);
    }
}


