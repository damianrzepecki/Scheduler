$(document).ready(function () {
    $('.table .eBtn1').on('click',function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href,function (client,status) {
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
    $('.table .eBtn2').on('click',function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href,function (client,status) {
            $('.myFormToUpdate #id').val(client.id);
            $('.myFormToUpdate #name').val(client.name);
            $('.myFormToUpdate #surname').val(client.surname);
            $('.myFormToUpdate #dateOfBirth').val(client.dateOfBirth);
            $('.myFormToUpdate #phoneNumber').val(client.phoneNumber);
            $('.myFormToUpdate #email').val(client.email);
            $('.myFormToUpdate #dateRegistered').val(client.dateRegistered);
        });
        $('.myFormToUpdate #editClientModal').modal();
    });
});

$(document).ready(function () {
$('.table .delBtn ').on('click',function (event) {
    event.preventDefault();
    var href = $(this).attr('href');
    $('.myFormToDelete #delRef').attr('href',href);
    $('.myFormToDelete #deleteModal').modal();

    });
});