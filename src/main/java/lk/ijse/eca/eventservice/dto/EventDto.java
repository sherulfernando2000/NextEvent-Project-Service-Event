package lk.ijse.eca.eventservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    public interface OnCreate {}

    @NotBlank(groups = OnCreate.class, message = "Event ID is required")
    @Pattern(groups = OnCreate.class, regexp = "^[A-Z]+$", message = "Event ID must contain uppercase letters only (A-Z)")
    private String eventId;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}
