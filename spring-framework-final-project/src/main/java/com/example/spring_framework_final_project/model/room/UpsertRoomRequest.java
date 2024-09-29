package com.example.spring_framework_final_project.model.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertRoomRequest {
    @NotBlank(message = "Название комнаты должно быть указано!")
    private String title;
    @NotBlank(message = "Описание комнаты должно быть заполнено!")
    private String description;
    @NotNull(message = "Номер комнаты должен быть заполнен!")
    @Positive(message = "Номер комнаты должен быть больше 0!")
    private Long number;
    @NotNull(message = "Стоимость комнаты должно быть указано!")
    @Positive(message = "Стоимость комнаты должно быть больше 0!")
    private Long cost;
    @NotNull(message = "Вместимость комнаты должна быть указано!")
    @Positive(message = "Вместимость комнаты должна быть больше 0!")
    private Long maxOccupancy;
    @NotNull(message = "ID отеля должен быть указан!")
    @Positive(message = "ID отеля должен быть больше 0!")
    private Long hotelId;

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getHotelId() {
        return hotelId;
    }
}
