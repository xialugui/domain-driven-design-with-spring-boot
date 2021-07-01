const headOfficeUri = "http://localhost:23333/headOffice";
getHeadOffice();

function getHeadOffice() {
    $.get(headOfficeUri, function (data, status) {
        $('#cash').html(data.amount);
        $('#balance').html(data.balance);
    });
}

const snackMachineUri = "http://localhost:23333/snackmachines";
getSnackMachines();

function getSnackMachines() {
    $.get(snackMachineUri, function (data, status) {
        var list = data == null ? [] : (data instanceof Array ? data : [data]);
        $.each(list, function (index, item) {
            $('#snackMachineList').append(
                '<li><a href="../snackMachine/SnackMachineView.html?id=' + item.id + '">' + item.id + '</a>&nbsp&nbsp&nbsp&nbsp<span>$' + item.amount + '</span></li>');
        });
    });
}

const atmUri = "http://localhost:23333/atms";
getAtms();

function getAtms() {
    $.get(atmUri, function (data, status) {
        var list = data == null ? [] : (data instanceof Array ? data : [data]);
        $.each(list, function (index, item) {
            $('#atmList').append(
                '<li><a href="../atm/AtmView.html?id=' + item.id + '">' + item.id + '</a>&nbsp&nbsp&nbsp&nbsp<span>$' + item.amount + '</span> </li>');
        });
    });
}

$("#btnUnload").click(function () {
    var snackmachineId = $('#snackmachineId').val();
    $.ajax({
        url: headOfficeUri + '/' + snackmachineId + '/unloadCash',
        type: 'PUT',
        success: function (data) {
        }
    });
});
$("#btnLoad").click(function () {
    var atmId = $('#atmId').val();
    $.ajax({
        url: headOfficeUri + '/' + atmId + '/loadCash',
        type: 'PUT',
        success: function (data) {
        }
    });
});