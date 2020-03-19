package pl.sda.scheduler.appointments;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.sda.scheduler.clients.Client;
import pl.sda.scheduler.clients.ClientMapper;

@Mapper(uses = ClientMapper.class)
public abstract class AppointmentMapper {
    @Mapping(target = "client", ignore = true)
    public abstract AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);

    @Mapping(target = "client", ignore = true)
    public abstract Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO);

    @AfterMapping
    protected void mapAppointments(Appointment appointment, @MappingTarget AppointmentDTO appointmentDTO) {
        appointmentDTO.setClientData(appointment.getClient().getName() + " " + appointment.getClient().getSurname());
    }

    @AfterMapping
    protected void mapAppointments(AppointmentDTO appointmentDTO, @MappingTarget Appointment appointment) {
        Client client = new Client();
        client.setId(appointmentDTO.getClientId());
        appointment.setClient(client);
    }

}
