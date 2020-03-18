package pl.sda.scheduler.appointments;

import org.mapstruct.Mapper;
import pl.sda.scheduler.clients.ClientMapper;

@Mapper(uses = {ClientMapper.class})
public interface AppointmentMapper {

    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
    Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO);

//
//    @AfterMapping
//    default void setClientData(Appointment appointment, @MappingTarget AppointmentDTO appointmentDTO) {
//        long id = appointmentDTO.getClientId();
//        String clientData = "";
//        if (clientData.equals("")) {
//            clientData = "error";
//        }
//        appointmentDTO.setClientData(clientData);
//    }
//
//    @Named("clientNameAndSurname")
//    static String userNameAndSurname(Appointment appointment) {
//        return appointment.getClient().getName() + " " + appointment.getClient().getSurname();
//    }
}
