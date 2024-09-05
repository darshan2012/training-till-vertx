package com.lms.util;

import java.time.LocalDateTime;

@FunctionalInterface
public interface PenaltyCalculator {
    long calculatePenalty(LocalDateTime issueDate, LocalDateTime returnDate);
}
