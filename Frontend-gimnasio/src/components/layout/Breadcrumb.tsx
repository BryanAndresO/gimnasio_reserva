import React from 'react';
import { Link } from 'react-router-dom';
import { classNames } from '../../utils/helpers';

interface BreadcrumbItem {
  label: string;
  path?: string;
}

interface BreadcrumbProps {
  items: BreadcrumbItem[];
}

export const Breadcrumb: React.FC<BreadcrumbProps> = ({ items }) => {
  return (
    <nav className="flex mb-4" aria-label="Breadcrumb">
      <ol className="flex items-center space-x-2">
        {items.map((item, index) => (
          <li key={index} className="flex items-center">
            {index > 0 && <span className="mx-2 text-gray-400">/</span>}
            {item.path && index < items.length - 1 ? (
              <Link
                to={item.path}
                className="text-blue-600 hover:text-blue-800"
              >
                {item.label}
              </Link>
            ) : (
              <span className={classNames(
                index === items.length - 1 ? 'text-gray-900 font-medium' : 'text-gray-500'
              )}>
                {item.label}
              </span>
            )}
          </li>
        ))}
      </ol>
    </nav>
  );
};

