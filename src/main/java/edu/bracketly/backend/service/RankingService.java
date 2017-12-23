package edu.bracketly.backend.service;

import edu.bracketly.backend.model.entity.user.K_FACTOR;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RankingService {

    public static final long STARTING_RANK = 1500;
    private static final int AVERAGE_GAMES_THRESHOLD = 30;
    private static final int PRO_RANK_THRESHOLD = 2500;
    private static final int WINNER_SCORE = 1;
    private static final int LOSER_SCORE = 0;

    @Autowired
    private UserRepository userRepository;

    public void registerWin(User winner, User loser) {
        updateScore(winner, WINNER_SCORE, loser, LOSER_SCORE);
    }

    private void updateScore(User one, int oneScore, User two, int twoScore) {
        incrementGamesPlayed(one);
        incrementGamesPlayed(two);

        long oneRank = one.getDetails().getRank();
        long twoRank = two.getDetails().getRank();

        double qOne = Math.pow(10, (oneRank / 400));
        double qTwo = Math.pow(10, (twoRank / 400));

        double eOne = qOne / (qOne + qTwo);
        double eTwp = qTwo / (qOne + qTwo);

        double adjustedOneRank = oneRank + one.getDetails().getKFactor().value * (oneScore - eOne);
        double adjustedTwoRank = twoRank + two.getDetails().getKFactor().value * (twoScore - eTwp);

        one.getDetails().setRank((long) adjustedOneRank);
        two.getDetails().setRank((long) adjustedTwoRank);

        updateKFactorIfNeeded(one);
        updateKFactorIfNeeded(two);

        userRepository.save(Arrays.asList(one, two));
    }

    private void updateKFactorIfNeeded(User user) {
        K_FACTOR kFactor = user.getDetails().getKFactor();
        if (kFactor == K_FACTOR.PRO) return;
        if (kFactor == K_FACTOR.BEGGINER && user.getDetails().getGamesPlayed() >= AVERAGE_GAMES_THRESHOLD) {
            user.getDetails().setKFactor(K_FACTOR.AVERAGE);
        }
        if (kFactor == K_FACTOR.AVERAGE && user.getDetails().getRank() > PRO_RANK_THRESHOLD) {
            user.getDetails().setKFactor(K_FACTOR.PRO);
        }
    }

    private void incrementGamesPlayed(User user) {
        user.getDetails().setGamesPlayed(user.getDetails().getGamesPlayed() + 1);
    }

}
