<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Db\Sql;

/**
 * Description of SqlBuilder
 *
 * @author Setia Budi Halim
 */
abstract class SqlBuilder
{

    protected $tableRef    = '';
    protected $joinPhrase  = '';
    protected $wherePhrase = '';
    protected $limitPhrase = '';

    /**
     *
     * @var \PDO
     */
    protected $pdo;

    abstract public function getSql();

    public function __construct(\PDO $pdo)
    {
        $this->pdo = $pdo;
    }

    public function __toString()
    {
        return $this->getSql();
    }

    /**
     * Set pivot table
     * @param string $table
     * @param string|null $alias
     * @return static
     */
    public function useTable($table, $alias = null)
    {
        $this->tableRef = $this->alias($table, $alias);
        return $this;
    }

    /**
     * Set pivot table
     * @param string $table
     * @param string|null $alias
     * @return static
     */
    public function useVirtualTable($table, $alias)
    {
        if (empty($alias)) {
            throw new \InvalidArgumentException('Subquery table must have an alias');
        }
        return $this->useTable('(' . $table . ')', $alias);
    }

    /**
     * Link a table with inner join
     * @param string $table
     * @param string $alias
     * @param string $condition Condition expression in SQL
     * @return static
     */
    public function innerJoin($table, $alias = null, $condition = null)
    {
        return $this->join(SqlKeyword::INNER_JOIN, $table, $alias, $condition);
    }

    /**
     * Link a table with left outer join
     * @param string $table
     * @param string $alias
     * @return static
     */
    public function leftJoin($table, $alias = null, $condition = null)
    {
        return $this->join(SqlKeyword::LEFT_JOIN, $table, $alias, $condition);
    }

    /**
     * Link a table with right outer join
     * @param string $table
     * @param string $alias
     * @param string $condition Condition expression in SQL
     * @return static
     */
    public function rightJoin($table, $alias = null, $condition = null)
    {
        return $this->join(SqlKeyword::RIGHT_JOIN, $table, $alias, $condition);
    }

    /**
     * Link a table with cross join
     * @param string $table
     * @param string $alias
     * @param string $condition Condition expression in SQL
     * @return static
     */
    public function crossJoin($table, $alias = null)
    {
        return $this->join(SqlKeyword::CROSS_JOIN, $table, $alias);
    }

    /**
     * 
     * @return static
     */
    public function clearJoin()
    {
        $this->joinPhrase = '';
        return $this;
    }

    /**
     * 
     * @param string $condition
     * @param string $relation Relation to other clauses, can be AND or OR see SqlKeyword::OPERATOR_*
     * @return static
     * @throws \InvalidArgumentException
     */
    public function where($condition, $relation = SqlKeyword::OPERATOR_AND)
    {
        if (empty($relation)) {
            $relation = SqlKeyword::OPERATOR_AND;
        }

        if (empty($this->wherePhrase)) {
            $this->wherePhrase = 'where ' . $condition;
        } else {
            $this->wherePhrase .= ' ' . $relation . ' ' . $condition;
        }
        return $this;
    }

    /**
     * 
     * @return static
     */
    public function clearWhere()
    {
        $this->wherePhrase = '';
        return $this;
    }

    /**
     * 
     * @param int $limit 0 or empty means no limit
     * @param int $offset Zero based row offset. Empty means start the first row.
     * @return static
     */
    public function limit($limit, $offset = 0)
    {
        $rowLimit = (int) $limit;
        if (empty($rowLimit)) {
            $this->limitPhrase = '';
            return $this;
        }

        $rowOffset = (int) $offset;
        if (empty($rowOffset)) {
            $this->limitPhrase = 'limit ' . $rowLimit;
        } else {
            $this->limitPhrase = 'limit ' . $rowLimit . ' offset ' . $rowOffset;
        }
        return $this;
    }

    /**
     * Quote a value for SQL statement
     * @param mixed $value
     * @param int $type All \PDO::PARAM_*
     * @return string
     */
    public function quoteValue($value, $type = \PDO::PARAM_STR)
    {
        return $this->pdo->quote($value, $type);
    }

    /**
     * 
     * @param string $query
     * @param string $alias
     */
    public function aliasQuery($query, $alias)
    {
        return $this->alias('(' . $query . ')', $alias);
    }

    /**
     * 
     * @param string $object
     * @param string $alias
     * @return string
     */
    protected function alias($object, $alias)
    {
        if (empty($alias)) {
            return $object;
        }
        return $object . ' as ' . $alias;
    }

    /**
     * 
     * @param string $joinType
     * @param string $table
     * @param string $alias
     * @param string $condition
     * @return static
     * @throws \DomainException
     */
    protected function join($joinType, $table, $alias = null, $condition = null)
    {
        $phrase = $joinType . ' ' . $this->alias($table, $alias);

        if ('' !== (string) $condition) {
            $phrase .= " on $condition";
        }

        $this->appendExpression($this->joinPhrase, $phrase);
        return $this;
    }

    /**
     * 
     * @param string $original
     * @param string $append
     * @param string $glue
     * @param string $start
     */
    protected function appendExpression(&$original, $append, $glue = ' ')
    {
        if ('' === (string) $original) {
            $original = $append;
        } else {
            $original .= $glue . $append;
        }
    }

    /**
     * 
     * @return string
     */
    protected function getJoinPhrase()
    {
        return $this->joinPhrase;
    }

    /**
     * 
     * @return string
     */
    protected function getWherePhrase()
    {
        return $this->wherePhrase;
    }

    /**
     * 
     * @return string
     */
    protected function getLimitPhrase()
    {
        return $this->limitPhrase;
    }

}
