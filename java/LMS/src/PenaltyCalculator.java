import java.time.LocalDateTime;

@FunctionalInterface
public interface PenaltyCalculator {
    long calculatePenalty(LocalDateTime dueDate, LocalDateTime returnDate);
}
