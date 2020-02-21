$(document).ready(function () {
    $('.table .eBtn1').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (client) {
            $('.myFormToAddNew #name').val(client.name);
            $('.myFormToAddNew #surname').val(client.surname);
            $('.myFormToAddNew #dateOfBirth').val(client.dateOfBirth);
            $('.myFormToAddNew #phoneNumber').val(client.phoneNumber);
            $('.myFormToAddNew #email').val(client.email);
        });
        $('.myFormToAddNew #addClientModal').modal();
    });
});
$(document).ready(function () {
    $('.table .eBtn2').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (client) {
            $('.myFormToUpdate #id').val(client.id);
            $('.myFormToUpdate #name').val(client.name);
            $('.myFormToUpdate #surname').val(client.surname);
            $('.myFormToUpdate #dateOfBirth').val(client.dateOfBirth);
            $('.myFormToUpdate #phoneNumber').val(client.phoneNumber);
            $('.myFormToUpdate #email').val(client.email);
        });
        $('.myFormToUpdate #editClientModal').modal();
    });
});
$(document).ready(function () {
    $('.table .delBtn ').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('.myFormToDelete #delRef').attr('href', href);
        $('.myFormToDelete #deleteModal').modal();
    });
});
$(document).ready(function () {
    $('.table .treatBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (client) {
            $('.myFormToAddAppointment #id').val(client.id);
            $('.myFormToAddAppointment #clientId').val(client.id);
        });
        $('.myFormToAddAppointment #addAppointmentModal').modal();
    });
});
$(document).ready(function () {
    $('.table .eBtnEditAppo').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (appointment) {
            $('.myFormToUpdateAppo #id').val(appointment.id);
            $('.myFormToUpdateAppo #chosenDay').val(appointment.chosenDay);
            $('.myFormToUpdateAppo #chosenHour').val(appointment.chosenHour);
            $('.myFormToUpdateAppo #nameOfTreatment').val(appointment.nameOfTreatment);
            $('.myFormToUpdateAppo #price').val(appointment.price);
            $('.myFormToUpdateAppo #clientId').val(appointment.clientId);
            $('.myFormToUpdateAppo #clientData').val(appointment.clientData);
        });
        $('.myFormToUpdateAppo #editAppoModal').modal();
    });
});
$(document).ready(function () {
    $('.table .delBtnAppo ').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('.myFormToDeleteAppo #delRef1').attr('href', href);
        $('.myFormToDeleteAppo #deleteAppoModal').modal();
    });
});
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('?lang=' + selectedOption);
        }
    });
});