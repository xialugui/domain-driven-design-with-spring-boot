let searchParams = new URLSearchParams(window.location.search)
let param = '';
if (searchParams.has('id')) {
    param = searchParams.get('id')
} else {
    param = '1';
}

const rootURI = "http://localhost:23333/snackmachines/" + param;
getSnackMachine();

function getSnackMachine() {
    $.get(rootURI, function (data, status) {
        console.log(data);
        $('#moneyInserted').html(data.moneyInTransaction);
        $('#chocolatePrice').html(data.slotDtoList[0].price);
        $('#sodaPrice').html(data.slotDtoList[1].price);
        $('#gumPrice').html(data.slotDtoList[2].price);
        $('#chocolateQuantity').html(data.slotDtoList[0].quantity);
        $('#sodaQuantity').html(data.slotDtoList[1].quantity);
        $('#gumQuantity').html(data.slotDtoList[2].quantity);
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
        case "btnBuyChocolate" :
            buy("1")
            break;
        case "btnBuySoda" :
            buy("2")
            break;
        case "btnBuyGum" :
            buy("3")
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
        }
    });
    location.reload();
}

function returnMoney() {
    $.ajax({
        url: rootURI + '/moneyInTransaction',
        type: 'PUT',
        success: function (result) {
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
