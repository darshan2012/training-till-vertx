import java.time.LocalDateTime;

public interface PenaltyCalculator {
    long calculatePenalty(LocalDateTime dueDate, LocalDateTime returnDate);
}
