import React from 'react';
import { classNames } from '../../utils/helpers';

interface Column<T> {
  header: string;
  accessor: keyof T | ((row: T) => React.ReactNode);
  className?: string;
}

interface TableProps<T> {
  data: T[];
  columns: Column<T>[];
  onRowClick?: (row: T) => void;
  className?: string;
}

export function Table<T extends { id?: string | number }>({
  data,
  columns,
  onRowClick,
  className,
}: TableProps<T>) {
  const renderCell = (row: T, accessor: Column<T>['accessor']) => {
    if (typeof accessor === 'function') {
      return accessor(row);
    }
    return String(row[accessor]);
  };

  return (
    <div className={classNames('overflow-x-auto', className)}>
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            {columns.map((column, index) => (
              <th
                key={index}
                className={classNames(
                  'px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider',
                  column.className
                )}
              >
                {column.header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {data.map((row, rowIndex) => (
            <tr
              key={row.id || rowIndex}
              onClick={() => onRowClick?.(row)}
              className={classNames(
                onRowClick && 'cursor-pointer hover:bg-gray-50'
              )}
            >
              {columns.map((column, colIndex) => (
                <td
                  key={colIndex}
                  className={classNames(
                    'px-6 py-4 whitespace-nowrap text-sm text-gray-900',
                    column.className
                  )}
                >
                  {renderCell(row, column.accessor)}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

