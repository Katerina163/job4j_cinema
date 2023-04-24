package ru.job4j.cinema.dto;

public class HallDto {
    private int maxRow;
    private int maxPlace;

    public HallDto() {
    }

    public HallDto(int maxRow, int maxPlace) {
        this.maxRow = maxRow;
        this.maxPlace = maxPlace;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getMaxPlace() {
        return maxPlace;
    }

    public void setMaxPlace(int maxPlace) {
        this.maxPlace = maxPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HallDto hallDto = (HallDto) o;
        if (maxRow != hallDto.maxRow) {
            return false;
        }
        return maxPlace == hallDto.maxPlace;
    }

    @Override
    public int hashCode() {
        int result = maxRow;
        result = 31 * result + maxPlace;
        return result;
    }
}
