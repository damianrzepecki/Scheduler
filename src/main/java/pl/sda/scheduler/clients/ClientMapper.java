package pl.sda.scheduler.clients;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.sda.scheduler.appointments.AppointmentMapper;

@Mapper(uses = AppointmentMapper.class)
public abstract class ClientMapper {
    @Mappings({@Mapping( target = "appointmentArrayList", ignore = true)})
    public  abstract ClientDTO clientToClientDTO(Client client);
    @Mappings({@Mapping(target = "appointmentArrayList", ignore = true)})
    public abstract  Client clientDTOtoClient(ClientDTO clientDTO);

}
