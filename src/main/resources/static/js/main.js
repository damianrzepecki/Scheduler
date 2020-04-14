$(document).ready(function () {
    $('.table .buttonToAddNewClient').on('click', function (event) {
        event.preventDefault();
        $('.myFormToAddNewClient #addClientModal').modal();
    });
});
$(document).ready(function () {
    $('.table .buttonToUpdateClient').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (client) {
            $('.myFormToUpdateClient #id').val(client.id);
            $('.myFormToUpdateClient #name').val(client.name);
            $('.myFormToUpdateClient #surname').val(client.surname);
            $('.myFormToUpdateClient #dateOfBirth').val(client.dateOfBirth);
            $('.myFormToUpdateClient #phoneNumber').val(client.phoneNumber);
            $('.myFormToUpdateClient #email').val(client.email);
        });
        $('.myFormToUpdateClient #updateClientModal').modal();
    });
});
$(document).ready(function () {
    $('.table .buttonToDeleteClient ').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('.myFormToDeleteClient #deleteClientButton').attr('href', href);
        $('.myFormToDeleteClient #deleteClientModal').modal();
    });
});
$(document).ready(function () {
    $('.table .buttonToAddAppointment').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (client) {
            $('.myFormToAddAppointment #id').val(client.id);
            $('.myFormToAddAppointment #clientId').val(client.id);
            $('.myFormToAddAppointment #clientData').val(client.name+" "+client.surname);
        });
        $('.myFormToAddAppointment #addAppointmentModal').modal();
    });
});
$(document).ready(function () {
    $('.table .buttonToEditAppointment').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (appointment) {
            $('.myFormToUpdateAppointment #id').val(appointment.id);
            $('.myFormToUpdateAppointment #chosenDay').val(appointment.chosenDay);
            $('.myFormToUpdateAppointment #chosenHour').val(appointment.chosenHour);
            $('.myFormToUpdateAppointment #nameOfTreatment').val(appointment.nameOfTreatment);
            $('.myFormToUpdateAppointment #hourFinished').val(appointment.hourFinished);
            $('.myFormToUpdateAppointment #price').val(appointment.price);
            $('.myFormToUpdateAppointment #clientId').val(appointment.clientId);
            $('.myFormToUpdateAppointment #clientData').val(appointment.clientData);
        });
        $('.myFormToUpdateAppointment #editAppointmentModal').modal();
    });
});
$(document).ready(function () {
    $('.table .buttonToDeleteAppointment ').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('.myFormToDeleteAppointment #deleteAppointmentButton').attr('href', href);
        $('.myFormToDeleteAppointment #deleteAppointmentModal').modal();
    });
});
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('?lang=' + selectedOption);
        };
    });
});

$(document).ready(function() {
    $('#chosenHour').on('change', function() {
        $('#hourFinished').val($(this).val());
    });
});
$(document).ready(function(){
    $('#hourFinished').bindWithDelay('change', function (){
    if (($(this).val())<($('#chosenHour').val())){
            alert('Godzina Zakończenia nie może być mniejsza niż rozpoczęcia')//TODO i18n for jquery
            $(function() {$('#hourFinished').val($('#chosenHour').val())});
            }
    },1500);
});

$(document).ready(function () {
    $('#buttonAddMinutes').on('click',function(){
//     alert( $('#minutes').find(':selected').val()); //Alert pokazujący wybraną wartość
     document.getElementById('hourFinished').stepUp($('#minutes').find(':selected').val());
    });
});
$(document).ready(function () {
    $('#buttonSubtractMinutes').on('click',function(){
     document.getElementById('hourFinished').stepDown($('#minutes').find(':selected').val());
    });
});
//
$(document).ready(function() {
    $('#langChangePL').on('click', function () {
    $('#table').bootstrapTable('destroy').bootstrapTable({
          locale: 'pl-PL'});
    alert('PLPLPLP');
   });
});
$(document).ready(function() {
    $('#langChangeEN').on('click', function () {
    $('#table').bootstrapTable('destroy').bootstrapTable({
          locale: 'en-US'});
    alert('USUS');
   });
});

$(document).ready(function() {
$(document).on('submit', "#formToAddNewClient", function (event) {
        event.preventDefault();
        var $form = $('#formToAddNewClient');
        var serializedForm = $form.serializeArray();
        $.ajax({
            type: 'POST',
            url: '/app/clients/save',
            data: $form.serialize(),
            dataType: "html",
            success: function(response) {
                if($(response).find('.errorFound').length){
                    $('body').removeClass('modal-open')
                    $('.modal-backdrop').remove()
                    $('#addNewClient').html(response)
                    $('#addClientModal').modal('show')
                    $('#name').val(serializedForm[1].value);
                    $('#surname').val(serializedForm[2].value);
                    $('#dateOfBirth').val(serializedForm[3].value)
                    $('#phoneNumber').val(serializedForm[4].value)
                    $('#email').val(serializedForm[5].value)
                }
                else{
                window.location.reload()
                }
            },
            error: function(error){
            alert(error)
            }
        });
        return false;
    });
});

$(document).ready(function() {
$(document).on('submit', "#formToUpdateClient", function (event) {
        event.preventDefault();
        var $form = $('#formToUpdateClient');
        var serializedForm = $form.serializeArray();
        $.ajax({
            type: 'POST',
            url: '/app/clients/update',
            data: $form.serialize(),
            dataType: "html",
            success: function(response) {
                if($(response).find('.errorFound').length){
                    console.log(serializedForm)
                    $('body').removeClass('modal-open')
                    $('.modal-backdrop').remove()
                    $('#updateClient').html(response)
                    $('#updateClientModal').modal('show')
                    $('#id').val(serializedForm[1].value);
                    console.log(serializedForm[2].value)
                    $('#name').val(serializedForm[2].value);
                    $('#surname').val(serializedForm[3].value);
                    $('#dateOfBirth').val(serializedForm[4].value)
                    $('#phoneNumber').val(serializedForm[5].value)
                    $('#email').val(serializedForm[6].value)
                }
                else{
                window.location.reload()
                }
            },
            error: function(error){
            alert(error)
            }
        });
        return false;
    });
});


