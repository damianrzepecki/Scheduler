package pl.sda.scheduler.appointments;

import org.springframework.stereotype.Component;
import pl.sda.scheduler.clients.Client;

import java.time.LocalDate;


@Component
class AppointmentMapper {
    Appointment model(CreateNewAppointmentDTO newAppointmentDTO) {
        Appointment appointment = new Appointment();
        Client client = new Client();
        client.setId(newAppointmentDTO.getClientId());
        appointment.setNameOfTreatment(newAppointmentDTO.getNameOfTreatment());
        appointment.setHourFinished(newAppointmentDTO.getHourFinished());
        appointment.setPrice(newAppointmentDTO.getPrice());
        appointment.setChosenDay(LocalDate.parse(newAppointmentDTO.getChosenDay()));
        appointment.setChosenHour(newAppointmentDTO.getChosenHour());
        appointment.setClient(client);
        return appointment;
    }

    AppointmentDTO DTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setChosenDay(appointment.getChosenDay());
        appointmentDTO.setChosenHour(appointment.getChosenHour());
        appointmentDTO.setNameOfTreatment(appointment.getNameOfTreatment());
        appointmentDTO.setHourFinished(appointment.getHourFinished());
        appointmentDTO.setPrice(appointment.getPrice());
        appointmentDTO.setClientData(appointment.getClient().getName() + " " + appointment.getClient().getSurname());
        appointmentDTO.setClientId(appointment.getClient().getId());
        return appointmentDTO;
    }
}
