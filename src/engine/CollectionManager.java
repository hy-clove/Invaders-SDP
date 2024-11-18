package engine;

import engine.Achievement.AchievementList;
import engine.DrawManager.SpriteType;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CollectionManager {
    public static CollectionManager instance;

    /**
     * enemySpriteSet
     * index:
     * 0 - Enemy Sprite
     * 1 - Enemy Name
     * 2 - xPosition
     * 3 - yPosition
     */
    @Getter
    private Object[][] enemySprite = {
            {DrawManager.SpriteType.EnemyShipA1, "Enemy_A1", 100, 180},
            {DrawManager.SpriteType.EnemyShipA2, "Enemy_A2", 100, 235},
            {DrawManager.SpriteType.EnemyShipB1, "Enemy_B1", 100, 290},
            {DrawManager.SpriteType.EnemyShipB2, "Enemy_B2", 100, 345},
            {DrawManager.SpriteType.EnemyShipC1, "Enemy_C1", 100, 400},
            {DrawManager.SpriteType.EnemyShipC2, "Enemy_C2", 100, 455},
            {DrawManager.SpriteType.ExplosiveEnemyShip1, "Explosive_Enemy_1", 100, 510},
            {DrawManager.SpriteType.ExplosiveEnemyShip2, "Explosive_Enemy_2", 100, 565},
            {DrawManager.SpriteType.EnemyShipSpecial, "Special_Enemy",95, 630}
    };
    /**
     * itemSpriteSet
     * index:
     * 0 - Item Sprite
     * 1 - Item Name
     * 2 - xPosition
     * 3 - yPosition
     */
    @Getter
    private Object[][] itemSprite = {
            {SpriteType.ItemCoin, "Coin", 100, 180},
            {SpriteType.ItemBomb, "Bomb", 100, 235},
            {SpriteType.ItemHeart, "Heart", 100, 290},
            {SpriteType.ItemBarrier, "Barrier", 100, 345},
            {SpriteType.ItemPierce, "Pierce", 100, 400},
            {SpriteType.ItemFeverTime, "FeverTime", 100, 455},
            {SpriteType.ItemSpeedUp, "SpeedUp", 100, 510},
            {SpriteType.ItemSpeedSlow, "SpeedSlow", 100, 565}
    };


    /**
     * Use AchievementList Because Achievement is not working
     * index:
     * 0 - Achievement Name
     * 1 - Achievement Description
     * 2 - xPosition
     * 3 - yPosition
     */
    AchievementList achievementList = new AchievementList();
    private final int XPosition = 60;
    @Getter
    private Object[][] AchievementSet_1 = {
            {achievementList.getACHIEVEMENT_LIVE().getName(), achievementList.getACHIEVEMENT_LIVE().getDescription(), XPosition, 180},
            {achievementList.getACHIEVEMENT_KILL_25().getName(), achievementList.getACHIEVEMENT_KILL_25().getDescription(), XPosition, 280},
            {achievementList.getACHIEVEMENT_KILL_100().getName(), achievementList.getACHIEVEMENT_KILL_100().getDescription(), XPosition, 380},
            {achievementList.getACHIEVEMENT_KILL_500().getName(), achievementList.getACHIEVEMENT_KILL_500().getDescription(), XPosition, 580},
            {achievementList.getACHIEVEMENT_KILL_1000().getName(), achievementList.getACHIEVEMENT_KILL_1000().getDescription(), XPosition, 480},
    };
    @Getter
    private Object[][] AchievementSet_2 = {
            {achievementList.getACHIEVEMENT_TRIALS_1().getName(), achievementList.getACHIEVEMENT_TRIALS_1().getDescription(), XPosition, 180},
            {achievementList.getACHIEVEMENT_TRIALS_10().getName(), achievementList.getACHIEVEMENT_TRIALS_10().getDescription(), XPosition, 380},
            {achievementList.getACHIEVEMENT_TRIALS_50().getName(), achievementList.getACHIEVEMENT_TRIALS_50().getDescription(), XPosition, 580},
    };
    @Getter
    private Object[][] AchievementSet_3 = {
            {achievementList.getACHIEVEMENT_KILLSTREAKS_3().getName(), achievementList.getACHIEVEMENT_KILLSTREAKS_3().getDescription(), XPosition, 180},
            {achievementList.getACHIEVEMENT_KILLSTREAKS_5().getName(), achievementList.getACHIEVEMENT_KILLSTREAKS_5().getDescription(), XPosition, 380},
            {achievementList.getACHIEVEMENT_KILLSTREAKS_7().getName(), achievementList.getACHIEVEMENT_KILLSTREAKS_7().getDescription(), XPosition, 580},
    };
    @Getter
    private Object[][] AchievementSet_4 = {
            {achievementList.getACHIEVEMENT_ACCURACY_60().getName(), achievementList.getACHIEVEMENT_ACCURACY_60().getDescription(), XPosition, 180},
            {achievementList.getACHIEVEMENT_ACCURACY_75().getName(), achievementList.getACHIEVEMENT_ACCURACY_75().getDescription(), XPosition, 380},
            {achievementList.getACHIEVEMENT_ACCURACY_85().getName(), achievementList.getACHIEVEMENT_ACCURACY_85().getDescription(), XPosition, 580},
    };
    @Getter
    private Object[][] AchievementSet_5 = {
            {achievementList.getACHIEVEMENT_SCORE_6000().getName(), achievementList.getACHIEVEMENT_SCORE_6000().getDescription(), XPosition, 180},
            {achievementList.getACHIEVEMENT_SCORE_15000().getName(), achievementList.getACHIEVEMENT_SCORE_15000().getDescription(), XPosition, 380},
            {achievementList.getACHIEVEMENT_SCORE_30000().getName(), achievementList.getACHIEVEMENT_SCORE_30000().getDescription(), XPosition, 580},
    };
    @Getter
    private Object[][] AchievementSet_6 = {
            {achievementList.getACHIEVEMENT_STAGE_MAX().getName(), achievementList.getACHIEVEMENT_STAGE_MAX().getDescription(), XPosition, 180},
            {achievementList.getACHIEVEMENT_ALL().getName(), achievementList.getACHIEVEMENT_ALL().getDescription(), XPosition, 450},
    };

    /** Logger*/
    private static Logger logger;

    /** Item Array*/
    @Getter
    private int[] itemTypes = new int[8];
    /** Enemy Array*/
    @Getter
    private int[] enemyTypes = new int[8];
    /** Achievement Array*/
    @Getter
    private int[] achievementTypes = new int[19];

    /** Statistics type variables that contain collections*/
    @Getter
    Statistics collection;
    /** Statistics type List*/
    @Getter
    List<Statistics> collectionList;

    public CollectionManager() {
        logger = Globals.getLogger();
        collectionList = new ArrayList<>();

        try{
            Statistics loadedCollection = Globals.getFileManager().loadCollections();
            if (loadedCollection != null) {
                this.collection = loadedCollection;
                this.itemTypes = collection.getItemsArray();
                this.enemyTypes = collection.getEnemiesArray();
                this.achievementTypes = collection.getAchievementsArray();
            } else {
                this.collection = new Statistics(itemTypes, enemyTypes, achievementTypes);
            }
        } catch (IOException e) {
            logger.info("Couldn't load collections");
        }
        collectionList.add(collection);
    }

    public static CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }

    /**
     * Add Collection Player Gained Enemy
     * @param i index of itemTypesArray
     */
    public void AddCollectionItemTypes(int i) {
        itemTypes[i]++;
        collection.setItemsArray(itemTypes);
        logger.info("Added item type " + i + " to collection : " + itemTypes[i]);
    }

    /**
     * Add Collection Player killed Enemy
     * @param spriteType Player killed enemy type
     */
    public void AddCollectionEnemyTypes(SpriteType spriteType) {
        switch (spriteType){
            case EnemyShipA1:
                enemyTypes[0]++;
                break;
            case EnemyShipA2:
                enemyTypes[1]++;
                break;
            case EnemyShipB1:
                enemyTypes[2]++;
                break;
            case EnemyShipB2:
                enemyTypes[3]++;
                break;
            case EnemyShipC1:
                enemyTypes[4]++;
                break;
            case EnemyShipC2:
                enemyTypes[5]++;
                break;
            case ExplosiveEnemyShip1:
                enemyTypes[6]++;
                break;
            case ExplosiveEnemyShip2:
                enemyTypes[7]++;
                break;
            case EnemyShipSpecial:
                enemyTypes[8]++;
                break;
        }
        logger.info("Added enemy type " + spriteType + " to collection");
        collection.setEnemiesArray(enemyTypes);
    }

    /**
     * Add Collection Player Achievement Cleared
     * @param i Player cleared Achievement Number
     */
    public void AddCollectionAchievementTypes(int i) {
        achievementTypes[i]++;
        collection.setAchievementsArray(achievementTypes);
        logger.info("Added achievement type " + i + " to collection : " + achievementTypes[i]);
    }




}