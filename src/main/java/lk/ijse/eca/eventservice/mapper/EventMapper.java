package lk.ijse.eca.eventservice.mapper;

import lk.ijse.eca.eventservice.dto.EventDto;
import lk.ijse.eca.eventservice.entity.Event;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EventMapper {

    EventDto toDto(Event event);

    Event toEntity(EventDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "eventId", ignore = true)
    void updateEntity(EventDto dto, @MappingTarget Event event);
}
