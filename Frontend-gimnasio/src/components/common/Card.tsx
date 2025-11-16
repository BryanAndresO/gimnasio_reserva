import React from 'react';
import { classNames } from '../../utils/helpers';

interface CardProps {
  children: React.ReactNode;
  title?: string;
  className?: string;
  headerActions?: React.ReactNode;
}

export const Card: React.FC<CardProps> = ({
  children,
  title,
  className,
  headerActions,
}) => {
  return (
    <div className={classNames('bg-white rounded-lg shadow-md p-6', className)}>
      {(title || headerActions) && (
        <div className="flex items-center justify-between mb-4 pb-4 border-b">
          {title && (
            <h3 className="text-lg font-semibold text-gray-900">{title}</h3>
          )}
          {headerActions && <div>{headerActions}</div>}
        </div>
      )}
      <div>{children}</div>
    </div>
  );
};

