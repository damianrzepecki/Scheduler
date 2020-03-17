package pl.sda.scheduler.appointments;

import org.mapstruct.*;

@Mapper
public interface AppointmentMapper {
    @Mapping(target="clientData", ignore = true)
    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
    Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO);
    @AfterMapping
    default void setClientData(Appointment appointment, @MappingTarget AppointmentDTO appointmentDTO){
        appointmentDTO.setClientData(appointment.getClient().getName());
    }
        @Named("clientNameAndSurname")
        static String userNameAndSurname(Appointment appointment){
        return appointment.getClient().getName() + " " + appointment.getClient().getSurname();
    }
}
