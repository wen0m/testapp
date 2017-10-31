package com.example.wen0m.sampleapp.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;


@Entity
public class Building {

    @Id
    private Long id;
    
    @Index(unique = true)
    private String guid;

    @Property
    private String name;

    @Property
    private String finishing;

    @Property
    private String deadline;

    @Property
    private String address;

    @Property
    private String image;

    @Property
    private Double latitude;

    @Property
    private Double longitude;

    @Property
    private int apart_count;

    @ToOne
    private Region region;

    @ToMany(referencedJoinProperty = "subId")
    private List<Subways> subways;

    @ToMany(referencedJoinProperty = "priceId")
    private List<Min_Prices> min_prices;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1665081582)
    private transient BuildingDao myDao;

    @Generated(hash = 213977321)
    public Building(Long id, String guid, String name, String finishing,
            String deadline, String address, String image, Double latitude,
            Double longitude, int apart_count) {
        this.id = id;
        this.guid = guid;
        this.name = name;
        this.finishing = finishing;
        this.deadline = deadline;
        this.address = address;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.apart_count = apart_count;
    }

    @Generated(hash = 1037559675)
    public Building() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinishing() {
        return this.finishing;
    }

    public void setFinishing(String finishing) {
        this.finishing = finishing;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getApart_count() {
        return this.apart_count;
    }

    public void setApart_count(int apart_count) {
        this.apart_count = apart_count;
    }

    @Generated(hash = 943778065)
    private transient boolean region__refreshed;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1560779612)
    public Region getRegion() {
        if (region != null || !region__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RegionDao targetDao = daoSession.getRegionDao();
            targetDao.refresh(region);
            region__refreshed = true;
        }
        return region;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 1294683238)
    public Region peakRegion() {
        return region;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 459087428)
    public void setRegion(Region region) {
        synchronized (this) {
            this.region = region;
            region__refreshed = true;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1775593881)
    public List<Subways> getSubways() {
        if (subways == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubwaysDao targetDao = daoSession.getSubwaysDao();
            List<Subways> subwaysNew = targetDao._queryBuilding_Subways(id);
            synchronized (this) {
                if(subways == null) {
                    subways = subwaysNew;
                }
            }
        }
        return subways;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1822714659)
    public synchronized void resetSubways() {
        subways = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 135488334)
    public List<Min_Prices> getMin_prices() {
        if (min_prices == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Min_PricesDao targetDao = daoSession.getMin_PricesDao();
            List<Min_Prices> min_pricesNew = targetDao._queryBuilding_Min_prices(id);
            synchronized (this) {
                if(min_prices == null) {
                    min_prices = min_pricesNew;
                }
            }
        }
        return min_prices;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1078849440)
    public synchronized void resetMin_prices() {
        min_prices = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 760218331)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBuildingDao() : null;
    }

}

