package pl.sda.scheduler.appointments;

import org.springframework.stereotype.Component;
import pl.sda.scheduler.clients.Clients;


@Component
class AppointmentsMapper {
    Appointments model(CreateNewAppointmentDTO newAppointmentDTO) {
        Appointments appointments = new Appointments();
        Clients client = new Clients();
        client.setId(newAppointmentDTO.getClientId());
        appointments.setChosenDay(newAppointmentDTO.getChosenDay());
        appointments.setChosenHour(newAppointmentDTO.getChosenHour());
        appointments.setClients(client);
        return appointments;
    }

    AppointmentsDTO DTO(Appointments appointments) {
        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
        appointmentsDTO.setAppointmentId(appointments.getId());
        appointmentsDTO.setChosenDay(appointments.getChosenDay());
        appointmentsDTO.setChosenHour(appointments.getChosenHour());
        appointmentsDTO.setClientData(appointments.getClients().getName() + " " + appointments.getClients().getSurname());
        appointmentsDTO.setClientId(appointments.getClients().getId());
        return appointmentsDTO;
    }
}
