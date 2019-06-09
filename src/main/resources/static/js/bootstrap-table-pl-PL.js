($ => {
    $.fn.bootstrapTable.locales['pl-PL'] = {
        formatLoadingMessage () {
            return 'Ładowanie, proszę czekać'
        },
        formatRecordsPerPage (pageNumber) {
            return `${pageNumber} rekordów na stronę`
        },
        formatShowingRows (pageFrom, pageTo, totalRows) {
            return `Pozycja ${pageFrom} z ${pageTo} w sumie ${totalRows}`
        },
        formatDetailPagination (totalRows) {
            return `Widok ${totalRows} wierszy`
        },
        formatSearch () {
            return 'Szukaj'
        },
        formatNoMatches () {
            return 'Niestety, nic nie znaleziono'
        },
        formatPaginationSwitch () {
            return 'Hide/Show pagination'
        },
        formatRefresh () {
            return 'Odśwież'
        },
        formatToggle () {
            return 'Przełącz'
        },
        formatColumns () {
            return 'Kolumny'
        },
        formatFullscreen () {
            return 'Fullscreen'
        },
        formatAllRows () {
            return 'All'
        },
        formatAutoRefresh () {
            return 'Auto odsieżanie'
        },
        formatExport () {
            return 'Export data'
        },
        formatClearFilters () {
            return 'Clear filters'
        },
        formatJumpto () {
            return 'GO'
        },
        formatAdvancedSearch () {
            return 'Advanced search'
        },
        formatAdvancedCloseButton () {
            return 'Close'
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['pl-PL'])
})(jQuery);