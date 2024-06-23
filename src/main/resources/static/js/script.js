document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('print-button').addEventListener('click', print_report);
    function print_report() {
        const tables = document.querySelectorAll('table');
        const currentDate = new Date().toLocaleDateString();
        const printWindow = window.open('', '', 'height=800,width=800');

        printWindow.document.write('<html><head><title>Print List</title>');
        printWindow.document.write('<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">');
        printWindow.document.write('<style>@media print { @page { margin: 0; } body { margin: 1cm; } .table-dark { background-color: #343a40 !important; color: #fff !important; } }</style>');
        printWindow.document.write('</head><body>');
        printWindow.document.write('<div class="print-header">Zlagoda supermarket</div>');
        tables.forEach(table => {

            const printContent = table.cloneNode(true);
            const actionsColumnIndex = printContent.querySelector('thead tr').children.length - 1;
            printContent.querySelector('thead tr').children[actionsColumnIndex].remove();

            printContent.querySelectorAll('tbody tr').forEach(row => {

                row.children[actionsColumnIndex].remove();
            });
            printWindow.document.write(printContent.outerHTML);

        });

        printWindow.document.write('<div class="print-date">' + currentDate + '</div>');
        printWindow.document.write('</body></html>');
        printWindow.document.close();
        printWindow.print();
    }
});
