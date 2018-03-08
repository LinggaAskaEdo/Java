<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Db\Sql;

use Firstwap\Altamides\Data\DataOrder;

/**
 * Description of SelectQueryBuilder
 *
 * @author Setia Budi Halim
 */
class SelectQueryBuilder extends SqlBuilder
{

    protected $selection = '';
    protected $orderPhrase = '';
    protected $groupingPhrase = '';
    protected $havingClause = '';

    public function getSql()
    {
        $sql = $this->getSelectPhrase();
        $this->appendExpression($sql, $this->getFromPhrase());
        $this->appendExpression($sql, $this->getJoinPhrase());
        $this->appendExpression($sql, $this->getWherePhrase());
        $this->appendExpression($sql, $this->getGroupingPhrase());
        $this->appendExpression($sql, $this->getHavingPhrase());
        $this->appendExpression($sql, $this->getOrderPhrase());
        $this->appendExpression($sql, $this->getLimitPhrase());

        return $sql;
    }

    /**
     * 
     * @param type $expr
     * @param type $alias
     * @return static
     */
    public function select($expr, $alias = null)
    {
        $field = $this->alias($expr, $alias);
        $this->appendExpression($this->selection, $field, ', ');
        return $this;
    }

    /**
     * 
     * @param string $expr
     * @param bool $dir Direction, see 
     * @see DataOrder
     * @return static
     */
    public function order($expr, $dir = DataOrder::ASCENDING)
    {
        $dirKeyword = DataOrder::isDescending($dir) ? SqlKeyword::ORDER_DESC : SqlKeyword::ORDER_ASC;
        $order = $expr . ' ' . $dirKeyword;
        $this->appendExpression($this->orderPhrase, $order, ', ');
        return $this;
    }

    /**
     * 
     * @param array $order
     * @return static
     */
    public function reorder(array $order)
    {
        foreach ($order as $expr => $ascending) {
            $this->order($expr, $ascending);
        }
        return $this;
    }

    /**
     * 
     * @param type $expr
     * @param type $direction
     * @return static
     */
    public function group($expr, $direction = SqlKeyword::ORDER_ASC)
    {
        $phrase = $expr . ' ' . $direction;
        $this->appendExpression($this->groupingPhrase, $phrase, ', ');
        return $this;
    }

    public function ungroup()
    {
        $this->groupingPhrase = '';
        return $this;
    }

    /**
     * 
     * @param type $condition
     * @param type $relation
     * @return static
     * @throws \InvalidArgumentException
     */
    public function having($condition, $relation = SqlKeyword::OPERATOR_AND)
    {
        if (empty($relation)) {
            throw new \InvalidArgumentException('Relation type must be set');
        }
        $this->appendExpression($this->havingClause, $condition, $relation);
        return $this;
    }

    /**
     * 
     * @return static
     */
    public function resetHavingClause()
    {
        $this->havingClause = '';
        return $this;
    }

    /**
     * 
     * @return static
     */
    public function selectNone()
    {
        $this->selection = '';
        return $this;
    }

    protected function getSelectPhrase()
    {
        if ('' === $this->selection) {
            return 'select *';
        } else {
            return 'select ' . $this->selection;
        }
    }

    protected function getFromPhrase()
    {
        if ('' === $this->tableRef) {
            return '';
        } else {
            return 'from ' . $this->tableRef;
        }
    }

    protected function getJoinPhrase()
    {
        if ('' === $this->tableRef) {
            return '';
        } else {
            return parent::getJoinPhrase();
        }
    }

    protected function getOrderPhrase()
    {
        if ('' === $this->orderPhrase) {
            return '';
        } else {
            return 'order by ' . $this->orderPhrase;
        }
    }

    protected function getGroupingPhrase()
    {
        if ('' === $this->groupingPhrase) {
            return '';
        } else {
            return 'group by ' . $this->groupingPhrase;
        }
    }

    protected function getHavingPhrase()
    {
        if (('' === $this->havingClause) || ('' === $this->groupingPhrase)) {
            return '';
        } else {
            return 'having ' . $this->havingClause;
        }
    }

}
