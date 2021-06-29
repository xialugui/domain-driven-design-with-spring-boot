let searchParams = new URLSearchParams(window.location.search)
let param = '';
if (searchParams.has('id')) {
    param = searchParams.get('id')
} else {
    param = '1';
}
const rootURI = "http://localhost:23333/atms/" + param;
getAtm();

function getAtm() {
    $.get(rootURI, function (data, status) {
        $('#moneyInside').html(data.amount);
        $('#moneyCharged').html(data.moneyCharged);
        $('#cent').html(data.oneCentCount);
        $('#tenCent').html(data.tenCentCount);
        $('#quarter').html(data.quarterCount);
        $('#dollar').html(data.oneDollarCount);
        $('#fiveDollar').html(data.fiveDollarCount);
        $('#twentyDollar').html(data.twentyDollarCount);
    });
}

$("#btnTakeMoney").click(function () {
    var moneyToTake = $('#moneyToTake').val();
    $.ajax({
        url: rootURI + '/' + moneyToTake,
        type: 'PUT',
        success: function (data) {
            alert(data);
            location.reload();
        }
    });
});
