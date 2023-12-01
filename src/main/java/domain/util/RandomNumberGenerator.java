package domain.util;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator {
    public static int generateByLimitConstraint(final int maxValue) {
        return Randoms.pickNumberInRange(0, maxValue);
    }
}