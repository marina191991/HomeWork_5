package hw1ObjectClass;

import java.util.Set;

public class Barrier {
    private String nameBarrier;
    private Integer wasteEnergy;

    public Barrier(String nameBarrier, Integer wasteEnergy) {
        this.nameBarrier = nameBarrier;
        this.wasteEnergy = wasteEnergy;
    }

    public static Set<Barrier> addBarrierInSetList(Set<Barrier> setList, Barrier barrier) {
        setList.add(barrier);
        return null;
    }

    public String getNameBarrier() {
        return nameBarrier;
    }

    public void setNameBarrier(String nameBarrier) {
        this.nameBarrier = nameBarrier;
    }

    public Integer getWasteEnergy() {
        return wasteEnergy;
    }

    public void setWasteEnergy(Integer level) {
        this.wasteEnergy = level;
    }

    @Override
    public String toString() {
        return "Barrier{" +
                "nameBarrier='" + nameBarrier + '\'' +
                ", wasteEnergy=" + wasteEnergy +
                '}';
    }
}
