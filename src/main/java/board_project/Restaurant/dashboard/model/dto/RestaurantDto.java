package board_project.Restaurant.dashboard.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Getter
@Setter
public class RestaurantDto {
    @NotNull
    private Long restaurantId;

    @NotNull
    private String restaurantName;

    @NotNull
    private String location;


}
