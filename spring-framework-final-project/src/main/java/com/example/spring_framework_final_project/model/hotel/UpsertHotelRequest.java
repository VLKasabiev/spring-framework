package com.example.spring_framework_final_project.model.hotel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertHotelRequest {
    @NotBlank(message = "Имя отеля должно быть заполнено!")
    private String hotelname;
    @NotBlank(message = "Заголовок должен быть заполнен!")
    private String title;
    @NotBlank(message = "Имя города должно быть указано!")
    private String city;
    @NotBlank(message = "Адрес должен быть указан!")
    private String address;
    @NotNull(message = "Дистанция от центра должна быть указана!")
    @Positive(message = "Дистаеция от центра должна быть больше 0!")
    private Integer distanceFromCentre;
}
