const rootURI = "http://localhost:23333/snackmachines/1";
getSnackMachine();

function getSnackMachine() {
    $.get(rootURI, function (data, status) {
        console.log(data)
        $('#moneyInserted').html(data.moneyInTransaction);
        $('#cent').html(data.oneCentCount);
        $('#tenCent').html(data.tenCentCount);
        $('#quarter').html(data.quarterCount);
        $('#dollar').html(data.oneDollarCount);
        $('#fiveDollar').html(data.fiveDollarCount);
        $('#twentyDollar').html(data.twentyDollarCount);
    });
}

$("button").click(function () {
    switch (this.id) {
        case "btnInsertCent" :
            insert("Cent")
            break;
        case "btnInsertTenCent" :
            insert("TenCent")
            break;
        case "btnInsertQuarter" :
            insert("Quarter")
            break;
        case "btnInsertDollar" :
            insert("Dollar")
            break;
        case "btnInsertFiveDollar" :
            insert("FiveDollar")
            break;
        case "btnInsertTwentyDollar" :
            insert("TwentyDollar")
            break;
        case "btnReturnMoney" :
            returnMoney()
            break;
        case "btnBuy" :
            buy("1")
            break;
        default :
            break;
    }
});

function insert(coinOrNote) {
    $.ajax({
        url: rootURI + '/moneyInTransaction/' + coinOrNote,
        type: 'PUT',
        success: function (result) {
// Do something with the result
        }
    });
    location.reload();
}

function returnMoney() {
    $.ajax({
        url: rootURI + '/moneyInTransaction',
        type: 'PUT',
        success: function (result) {
// Do something with the result
        }
    });
    location.reload();
}

function buy(position) {
    $.ajax({
        url: rootURI + '/' + position,
        type: 'PUT',
        success: function (result) {
        }
    });
    location.reload();
}
