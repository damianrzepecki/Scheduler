package pl.sda.scheduler.appointments;

import org.springframework.stereotype.Component;
import pl.sda.scheduler.clients.Client;


@Component
class AppointmentMapper {
    Appointment model(CreateNewAppointmentDTO newAppointmentDTO) {
        Appointment appointment = new Appointment();
        Client client = new Client();
        client.setId(newAppointmentDTO.getClientId());
        appointment.setChosenDay(newAppointmentDTO.getChosenDay());
        appointment.setChosenHour(newAppointmentDTO.getChosenHour());
        appointment.setClient(client);
        return appointment;
    }

    AppointmentDTO DTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setChosenDay(appointment.getChosenDay());
        appointmentDTO.setChosenHour(appointment.getChosenHour());
        appointmentDTO.setClientData(appointment.getClient().getName() + " " + appointment.getClient().getSurname());
        appointmentDTO.setClientId(appointment.getClient().getId());
        return appointmentDTO;
    }
}
