package engine.Achievement;

import lombok.Getter;

import static engine.Globals.NUM_LEVELS;


public class AchievementList {
    @Getter private static final Achievement ACHIEVEMENT_LIVE            = (new Achievement("Aerobatics","Maintain Maximum Life",3, AchievementType.LIVES, 1));
    @Getter private static final Achievement ACHIEVEMENT_KILL_25         = (new Achievement("Rookie Pilot", "Destroy 25 enemies", 25, AchievementType.KILLS, 1));
    @Getter private static final Achievement ACHIEVEMENT_KILL_100        = (new Achievement("Space Hunter", "Destroy 100 enemies", 100, AchievementType.KILLS, 1));
    @Getter private static final Achievement ACHIEVEMENT_KILL_500        = (new Achievement("Space Trooper", "Destroy 500 enemies", 500, AchievementType.KILLS, 1));
    @Getter private static final Achievement ACHIEVEMENT_KILL_1000        = (new Achievement("Guardian of Universe", "Destroy 1000 enemies", 1000, AchievementType.KILLS, 1));

    @Getter private static final Achievement ACHIEVEMENT_TRIALS_1        = (new Achievement("Welcome Recruit", "Finished first game", 1, AchievementType.TRIALS, 1));
    @Getter private static final Achievement ACHIEVEMENT_TRIALS_10       = (new Achievement("Skilled Solider", "Finished 10th game", 10, AchievementType.TRIALS, 1));
    @Getter private static final Achievement ACHIEVEMENT_TRIALS_50       = (new Achievement("Veteran Pilot", "Finished 50th game", 50, AchievementType.TRIALS, 1));

    @Getter private static final Achievement ACHIEVEMENT_KILLSTREAKS_5   = (new Achievement("Gunslinger", "Kill 5 in a streak", 5, AchievementType.KILLSTREAKS, 1));
    @Getter private static final Achievement ACHIEVEMENT_KILLSTREAKS_15   = (new Achievement("Fear the Enemy", "Kill 15 in a streak", 15, AchievementType.KILLSTREAKS, 5));
    @Getter private static final Achievement ACHIEVEMENT_KILLSTREAKS_30   = (new Achievement("Genocide","Kill 30 in a streak", 30, AchievementType.KILLSTREAKS, 10));

    @Getter private static final Achievement ACHIEVEMENT_ACCURACY_60     = (new Achievement("Preheating","Accuracy 60%", 60, AchievementType.ACCURACY,5));
    @Getter private static final Achievement ACHIEVEMENT_ACCURACY_75     = (new Achievement("Overheating","Accuracy 75%", 75, AchievementType.ACCURACY, 5));
    @Getter private static final Achievement ACHIEVEMENT_ACCURACY_85     = (new Achievement("Runaway","Accuracy 85%", 85, AchievementType.ACCURACY, 5));

    @Getter private static final Achievement ACHIEVEMENT_SCORE_6000      = (new Achievement("First Milestone", "Reach 6,000 points", 6000, AchievementType.SCORE,1));
    @Getter private static final Achievement ACHIEVEMENT_SCORE_15000     = (new Achievement("Score Hunter", "Reach 15,000 points", 15000, AchievementType.SCORE,3));
    @Getter private static final Achievement ACHIEVEMENT_SCORE_30000     = (new Achievement("Score Master", "Reach 30,000 points", 30000, AchievementType.SCORE,5));

    @Getter private static final Achievement ACHIEVEMENT_DISTANCE_5     = (new Achievement("Beginner","Moved 5km",5000, AchievementType.DISTANCE, 1));
    @Getter private static final Achievement ACHIEVEMENT_DISTANCE_20     = (new Achievement("Explorer","Moved 20km",20000, AchievementType.DISTANCE, 3));
    @Getter private static final Achievement ACHIEVEMENT_DISTANCE_42     = (new Achievement("Marathon Runner","Moved 42km",42000, AchievementType.DISTANCE, 5));

    @Getter private static final Achievement ACHIEVEMENT_STAGE_MAX       = (new Achievement("Home Sweet Home","Cleared Final Stage", NUM_LEVELS, AchievementType.STAGE, 5));
    @Getter private static final Achievement ACHIEVEMENT_ALL             = (new Achievement("Medal of Honor", "Complete all achievements", 0, AchievementType.ALL, 10));



    @Getter
    private static final Achievement[] ALL_ACHIEVEMENTS = {
            ACHIEVEMENT_LIVE,
            ACHIEVEMENT_KILL_25,
            ACHIEVEMENT_KILL_100,
            ACHIEVEMENT_KILL_500,
            ACHIEVEMENT_KILL_1000,
            ACHIEVEMENT_TRIALS_1,
            ACHIEVEMENT_TRIALS_10,
            ACHIEVEMENT_TRIALS_50,
            ACHIEVEMENT_KILLSTREAKS_5,
            ACHIEVEMENT_KILLSTREAKS_15,
            ACHIEVEMENT_KILLSTREAKS_30,
            ACHIEVEMENT_ACCURACY_60,
            ACHIEVEMENT_ACCURACY_75,
            ACHIEVEMENT_ACCURACY_85,
            ACHIEVEMENT_SCORE_6000,
            ACHIEVEMENT_SCORE_15000,
            ACHIEVEMENT_SCORE_30000,
            ACHIEVEMENT_DISTANCE_5,
            ACHIEVEMENT_DISTANCE_20,
            ACHIEVEMENT_DISTANCE_42,
            ACHIEVEMENT_STAGE_MAX,
            ACHIEVEMENT_ALL
    };
}
